package com.things.scheduler.infrastructure.client;


import com.things.scheduler.business.dto.UserResponse;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users", url="${url.service.users}")
public interface UserClient {

    @GetMapping("/user")
    UserResponse getUserByEmail(@RequestHeader("Authorization") String token,
                                @RequestParam String email);
}
