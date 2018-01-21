/*
 * Copyright 2016.
 * This code is part of Buildup
 */

package buildup.services;

/**
 * Base interface for a login service
 */
public interface LoginService {

    void attemptLogin(String email, String password);
}
