package com.kuropatin.zenbooking.work.imitation.generator;

import com.kuropatin.zenbooking.model.request.AmountRequest;
import com.kuropatin.zenbooking.work.imitation.generator.util.GeneratorUtils;
import org.springframework.stereotype.Component;

@Component
public class AmountRequestGenerator implements Generator<AmountRequest> {

    @Override
    public AmountRequest generate() {
        return new AmountRequest(String.valueOf(GeneratorUtils.randomInt(100, 1000)));
    }
}