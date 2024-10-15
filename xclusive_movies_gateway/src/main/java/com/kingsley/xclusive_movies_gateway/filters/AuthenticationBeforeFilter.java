package com.kingsley.xclusive_movies_gateway.filters;
import com.kingsley.xclusive_movies_gateway.exceptions.UnauthorizedException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationBeforeFilter extends AbstractGatewayFilterFactory<AuthenticationBeforeFilter.Config> {

    public AuthenticationBeforeFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey("Authorization")){
                return Mono.error(new UnauthorizedException("Missing or invalid token"));
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }
}
