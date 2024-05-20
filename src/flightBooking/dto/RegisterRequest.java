package flightBooking.dto;

import flightBooking.model.GenderType;
import java.time.LocalDate;

public record RegisterRequest(String firstName, String lastName, String phoneNumber, String email, String tcNumber, String password, GenderType gender, LocalDate dateOfBirth) {

    public RegisterRequest(String firstName, String lastName, String phoneNumber, String email, String tcNumber, GenderType gender, LocalDate dateOfBirth) {
        this(firstName, lastName, phoneNumber, email, tcNumber, "", gender, dateOfBirth);
    }

    

}
