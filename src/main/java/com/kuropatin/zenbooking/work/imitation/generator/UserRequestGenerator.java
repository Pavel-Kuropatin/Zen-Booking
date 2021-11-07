package com.kuropatin.zenbooking.work.imitation.generator;

import com.kuropatin.zenbooking.model.Gender;
import com.kuropatin.zenbooking.model.request.UserCreateRequest;
import com.kuropatin.zenbooking.work.imitation.generator.util.GeneratorUtils;
import com.kuropatin.zenbooking.work.imitation.vocabulary.Vocabulary;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRequestGenerator implements Generator<UserCreateRequest> {

    private final Vocabulary vocabulary;

    @Override
    public UserCreateRequest generate() {
        final UserCreateRequest newUser = new UserCreateRequest();
        final Gender gender = generateGender();
        newUser.setLogin(generateLoginPassword());
        newUser.setPassword(generateLoginPassword());
        newUser.setName(vocabulary.getName(gender));
        newUser.setSurname(vocabulary.getSurname(gender));
        newUser.setGender(gender.name());
        newUser.setBirthDate(generateBirthDate());
        newUser.setEmail(generateEmail(newUser.getName(), newUser.getSurname(), newUser.getBirthDate()));
        newUser.setPhone(generatePhone());
        return newUser;
    }

    private String generateLoginPassword() {
        return RandomStringUtils.randomAlphanumeric(8, 20);
    }

    private Gender generateGender() {
        List<Gender> genders = Arrays.asList(Gender.MALE, Gender.FEMALE);
        return genders.get(GeneratorUtils.randomInt(0, 1));
    }

    private String generateBirthDate() {
        final int year = GeneratorUtils.randomInt(1960, 2002);
        final int month = GeneratorUtils.randomInt(1, 12);
        final int day = GeneratorUtils.randomInt(1, 28);
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
    }

    private String generateEmail(final String name, final String surname, final String birthDate) {
        return name.toLowerCase() + "." + surname.toLowerCase() + "-" + birthDate.substring(0, 4) + "@gmail.com";
    }

    private String generatePhone() {
        return "+375" + RandomStringUtils.randomNumeric(9);
    }
}