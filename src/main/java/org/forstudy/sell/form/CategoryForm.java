package org.forstudy.sell.form;

import lombok.Data;

@Data
public class CategoryForm {

    private Integer categoryId;

    /** ·类目名字 */
    private String categoryName;

    /** ·类目编码 */
    private Integer categoryType;
}
