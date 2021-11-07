package com.kuropatin.zenbooking.work.imitation.generator;

import com.kuropatin.zenbooking.work.imitation.vocabulary.Vocabulary;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {
        UserRequestGenerator.class, Vocabulary.class
})
class UserRequestGeneratorTest {

    @Autowired
    private UserRequestGenerator generator;

    @Test
    void testGenerate() {
        for (int i = 0; i < 10; i++) {
            System.out.println(ToStringBuilder.reflectionToString(generator.generate(), ToStringStyle.JSON_STYLE));
        }
        assertTrue(true);
    }
}