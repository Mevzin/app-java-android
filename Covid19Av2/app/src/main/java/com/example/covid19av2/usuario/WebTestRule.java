package com.example.covid19av2.usuario;

import com.example.covid19av2.Login;

public class IntentsTestRule {
    @Rule
    public IntentsTestRule<Login> intentsTestRule =
            new IntentsTestRule<>(Login.class);
}
