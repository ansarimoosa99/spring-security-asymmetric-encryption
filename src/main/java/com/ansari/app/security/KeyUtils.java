package com.ansari.app.security;

import com.ansari.app.exception.BusinessException;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {

    private KeyUtils() {}

    public static PrivateKey loadPrivateKey(final String pemPath) throws Exception {
        final String key = readKeyFromResource(pemPath).replace("-----BEGIN PRIVATE KEY-----","")
                                                        .replace("-----END PRIVATE KEY-----", "")
                                                        .replaceAll("\\s", "");

        final byte[] decoded = Base64.getDecoder().decode(key);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }


    public static PublicKey loadPublicKey(final String pemPath) throws Exception {
        final String key = readKeyFromResource(pemPath).replace("-----BEGIN PUBLIC KEY-----","")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        final byte[] decoded = Base64.getDecoder().decode(key);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private static String readKeyFromResource(final String pemPath) throws Exception {
        try(final InputStream is = KeyUtils.class.getResourceAsStream(pemPath)) {
            if(is == null){
                throw new IllegalArgumentException("Couldn't not found key file " + pemPath);
            }
            return new String(is.readAllBytes());
        }
    }
}



// Public and pvt Keys
// 2048 bit private key - using commant -
// openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048  [never share only auth server should have it]
// to generate public key from pvt kye
// openssl rsa -pubout -in private_key.pem -out public_key.pem  [can be shraed]

// we should never locally store pvt key in our source code[resources]
// we need to use a vault or AWS secret manager in oreder to store these files
// and read it from that location
// but in this project we are storing everything locally
