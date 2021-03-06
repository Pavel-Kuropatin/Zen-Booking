package com.kuropatin.zenbooking.model.request;

import com.kuropatin.zenbooking.validation.IntegerInRange;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AmountRequest {

    @IntegerInRange(min = 1, max = 1000, message = "Enter amount to deposit, should be between 1 and 1000")
    private Integer amount;
}