package az.ingress.client;

import az.ingress.decoder.CustomErrorDecoder;
import az.ingress.model.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-product", url = "${client.urls.ms-product}", configuration = CustomErrorDecoder.class)
public interface ProductClient {
    @GetMapping("internal/v1/products/{id}")
    ProductResponse getProductId(@PathVariable Long id);
}
