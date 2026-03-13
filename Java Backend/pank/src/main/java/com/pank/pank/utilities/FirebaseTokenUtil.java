package com.pank.pank.utilities;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Component;

@Component
public class FirebaseTokenUtil {

    public FirebaseToken verify(String idToken) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(idToken);
    }

    public String getDid(FirebaseToken token) throws FirebaseAuthException {
        return (String) token.getClaims().get("did");
    }

    public String getRole(FirebaseToken token) throws FirebaseAuthException {
        return (String) token.getClaims().get("role");
    }
}
