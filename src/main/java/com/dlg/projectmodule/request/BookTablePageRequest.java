package com.dlg.projectmodule.request;

import com.dlg.projectmodule.request.page.TablePageRequest;
import lombok.Data;

@Data
public class BookTablePageRequest extends TablePageRequest {
    private Integer price;
}
