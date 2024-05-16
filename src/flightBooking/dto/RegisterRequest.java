package flightBooking.dto;

public record RegisterRequest(String firstName, String lastName, String tcNumber, String email, String phoneNumber, String password) {

}
