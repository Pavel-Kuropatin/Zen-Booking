package com.kuropatin.zenbooking.scheduling;

import com.kuropatin.zenbooking.model.Order;
import com.kuropatin.zenbooking.service.OrderService;
import com.kuropatin.zenbooking.util.ApplicationTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public final class ScheduledTasks {

    private final OrderService orderService;

    private LocalDate lastCheckDate = ApplicationTimeUtils.getLocalDate();
    private boolean isCheckedForFinishToday = false;

    //Order is accepted automatically if it was not accepted, declined or cancelled within ~5 minutes after placing
    @Scheduled(fixedDelay = 60 * 1000, initialDelay = 60 * 1000)
    public void tryToAccept() {
        final List<Order> ordersToAccept = orderService.getOrdersToAutoAccept();
        if (!ordersToAccept.isEmpty()) {
            final LocalDateTime now = ApplicationTimeUtils.getLocalDateTime();
            for (final Order order : ordersToAccept) {
                if (order.getUpdated().toLocalDateTime().isBefore(now.minusMinutes(5))) {
                    log.trace(MessageFormat.format("Automatically accept order with id {0}", order.getId()));
                    orderService.acceptOrder(order.getId(), order.getProperty().getUser().getId());
                }
            }
        }
    }

    //Ended orders are finish automatically once a day or upon restart
    @Scheduled(fixedDelay = 60 * 1000, initialDelay = 10 * 1000)
    public void tryToFinish() {
        final LocalDate now = ApplicationTimeUtils.getLocalDate();
        if (now.isAfter(lastCheckDate)) {
            isCheckedForFinishToday = false;
            lastCheckDate = now;
        }
        if (!isCheckedForFinishToday) {
            final StopWatch timer = new StopWatch();
            timer.start();
            isCheckedForFinishToday = true;
            final List<Order> ordersToFinish = orderService.getOrdersToAutoFinish();
            for (final Order order : ordersToFinish) {
                if(order.getEndDate().isBefore(now)) {
                    log.trace(MessageFormat.format("Automatically finish order with id {0}", order.getId()));
                    orderService.autoFinishOrder(order.getId());
                }
            }
            timer.stop();
            log.trace("Orders finished in " + timer.getTotalTimeMillis() + " ms");
        }
    }
}