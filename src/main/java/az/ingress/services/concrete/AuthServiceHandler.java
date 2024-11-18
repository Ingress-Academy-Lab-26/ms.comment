package az.ingress.services.concrete;

import az.ingress.annotation.Log;
import az.ingress.client.AuthClient;
import az.ingress.services.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Log
@Service(value = "authServiceHandler")
@RequiredArgsConstructor
public class AuthServiceHandler implements AuthService {
    private final AuthClient authClient;

    @Override
    public void verify(String token) {
        authClient.verify(token);
    }

}