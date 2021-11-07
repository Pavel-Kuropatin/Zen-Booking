package com.kuropatin.zenbooking.work.imitation.service;

import com.kuropatin.zenbooking.service.UserService;
import com.kuropatin.zenbooking.work.imitation.generator.AmountRequestGenerator;
import com.kuropatin.zenbooking.work.imitation.generator.UserRequestGenerator;
import com.kuropatin.zenbooking.work.imitation.repository.FakeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImitationService {

    private final UserRequestGenerator userRequestGenerator;
    private final AmountRequestGenerator amountRequestGenerator;
    private final UserService userService;
    private final FakeUserRepository fakeUserRepository;

    public void createUser() {
        long userId = userService.createUser(userRequestGenerator.generate()).getId();
        userService.deposit(userId, amountRequestGenerator.generate());
        fakeUserRepository.createRecord(userId);
    }
}