package com.kuropatin.zenbooking.work.imitation.creator;

import com.kuropatin.zenbooking.service.UserService;
import com.kuropatin.zenbooking.work.imitation.generator.UserRequestGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreator implements Creator {

    private final UserRequestGenerator userRequestGenerator;
    private final UserService userService;

    @Override
    public void create() {
        userService.createUser(userRequestGenerator.generate());
    }
}