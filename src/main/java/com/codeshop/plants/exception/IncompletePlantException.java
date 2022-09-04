package com.codeshop.plants.exception;

public class IncompletePlantException extends RuntimeException {

    // TODO: What about unneeded fields like potSize?
    private static final String message = """
        The provided user data is incomplete. Expected format is:
        {
            name: string,
            waterInSeason: 0-5,
            waterOutOfSeason: 0-5,
            sun: 0-3,
            moisture: 0-3,
            potSize: (double)
        }
        """;

    public IncompletePlantException() {
        super(message);
    }
}
