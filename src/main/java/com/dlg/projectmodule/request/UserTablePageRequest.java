package com.dlg.projectmodule.request;

import com.dlg.projectmodule.request.page.TablePageRequest;
import lombok.Data;

@Data
public class UserTablePageRequest extends TablePageRequest {
    private Integer id;// 分页参数之外查询参数
}
