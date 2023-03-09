package com.beges.bomjGame.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatUserDTO {

    Long id;
    String userName;
    String firstName;
    boolean isPremium;

}
