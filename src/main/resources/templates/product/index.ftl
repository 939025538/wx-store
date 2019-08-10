<html>
    <#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">

        <#include "../common/nav.ftl">

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/sell/seller/product/save" method="post">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" value="${(productInfo.productName)!''}" name="productName"/>
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input type="text" class="form-control" value="${(productInfo.productPrice)!''}" name="productPrice"/>
                            </div>
                            <div class="form-group">
                                <label>库存</label>
                                <input type="number" class="form-control" value="${(productInfo.productStock)!''}" name="productStock"/>
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input type="text" class="form-control" value="${(productInfo.productDescription)!''}" name="productDescription"/>
                            </div>
                            <div class="form-group">
                                <label>图片</label>
                                <img src="${(productInfo.productIcon)!''}" alt="">
                                <input type="text" class="form-control" value="${(productInfo.productIcon)!''}" name="productIcon"/>
                            </div>
                            <div class="form-group">
                                <label>类目</label>
                                <select name="categoryType" class="form-contorl">
                                    <#list productCategoryList as category>
                                        <option value="${category.categoryType}" <#if productInfo?? && productInfo.categoryType == category.categoryType >
                                        selected
                                        </#if> >
                                            ${category.categoryName}
                                        </option>
                                    </#list>
                                </select>
                                </div>
                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>