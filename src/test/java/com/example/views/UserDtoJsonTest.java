package com.example.views;

import com.example.views.dto.UserDto;
import com.example.views.model.Order;
import com.example.views.model.Product;
import com.example.views.model.Status;
import com.example.views.view.Views;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserDtoJsonTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    Product product = new Product(1, "product", "some description");
    Order order = new Order(1, 1, List.of(product), 23.45, Status.READY);

    UserDto userDto = UserDto.builder()
            .id(1)
            .name("name")
            .email("sveta@mail.ru")
            .orders(List.of(order))
            .build();

    @Test
    public void serializeUserSummaryClass() throws Exception {
        String result = objectMapper.writerWithView(Views.UserSummary.class)
                .writeValueAsString(userDto);

        assertThat(result, containsString("1"));
        assertThat(result, containsString("name"));
        assertThat(result, containsString("sveta@mail.ru"));

        assertThat(result, not(containsString("orders")));
    }

    @Test
    public void serializeUserDetailsClass() throws Exception {
        String result = objectMapper.writerWithView(Views.UserDetails.class)
                .writeValueAsString(userDto);

        assertThat(result, containsString("1"));
        assertThat(result, containsString("name"));
        assertThat(result, containsString("sveta@mail.ru"));
        assertThat(result, containsString("orders"));
    }
}
