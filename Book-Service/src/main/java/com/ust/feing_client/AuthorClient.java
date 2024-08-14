package com.ust.feing_client;



import com.ust.dto.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Author-Service", url = "http://localhost:8200")

public interface AuthorClient {

    @GetMapping("/authors/{id}")
    Author getAuthorById(@PathVariable("id") Long id);
}
