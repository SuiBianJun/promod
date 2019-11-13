package com.dlg.projectmodule.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtVO {
    String token;
    String userId;
}
