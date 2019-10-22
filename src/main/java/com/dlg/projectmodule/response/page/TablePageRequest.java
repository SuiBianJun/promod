package com.dlg.projectmodule.response.page;

import lombok.Data;

@Data
public class TablePageRequest {

    private int page;
    private int limits;
    private String data;

}
