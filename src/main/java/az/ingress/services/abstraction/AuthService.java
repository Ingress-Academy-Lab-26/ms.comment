package az.ingress.services.abstraction;


public interface AuthService {
    void verify(String token);
}