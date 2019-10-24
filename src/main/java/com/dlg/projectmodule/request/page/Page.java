package com.dlg.projectmodule.request.page;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Page {
    int page;
    int limits;
}
