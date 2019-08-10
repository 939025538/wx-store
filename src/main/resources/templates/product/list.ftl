<html>
    <#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">

        <#include "../common/nav.ftl">

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoPage.getContent() as productInfoPage>
                                <tr>
                                    <td>${productInfoPage.productId}</td>
                                    <td>${productInfoPage.productName}</td>
                                    <td><img src="${productInfoPage.productIcon}" alt="" height="100px" width="100px"></td>
                                    <td>${productInfoPage.productPrice}</td>
                                    <td>${productInfoPage.productStock}</td>
                                    <td>${productInfoPage.productDescription}</td>
                                    <td>${productInfoPage.categoryType}</td>
                                    <td>${productInfoPage.createTime}</td>
                                    <td>${productInfoPage.updateTime}</td>
                                    <td><a href="/sell/seller/product/index?productId=${productInfoPage.productId}">修改</a></td>
                                    <td>
                                        <#if productInfoPage.getProductInfoEnums().getDescription() == "下架">
                                            <a href="/sell/seller/product/onSale?productId=${productInfoPage.productId}">上架</a>
                                        <#else>
                                            <a href="/sell/seller/product/offSale?productId=${productInfoPage.productId}">下架</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>

                    <#-- 分页 -->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <#if currentPage gt 1>
                                <li><a href="/sell/seller/product/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>

                            <#if productInfoPage.getTotalPages() gt 9>
                                <#if currentPage gt 4 && productInfoPage.getTotalPages()-currentPage gt 4 >
                                    <#list currentPage-4..currentPage+4 as index>
                                        <#if currentPage == index>
                                            <li class="disabled"> <a href="#">${index}</a> </li>
                                        <#else>
                                            <li> <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a> </li>
                                        </#if>
                                    </#list>
                                <#elseif productInfoPage.getTotalPages()-currentPage lte 4>
                                    <#list productInfoPage.getTotalPages()-9 .. productInfoPage.getTotalPages() as index>
                                        <#if currentPage == index>
                                            <li class="disabled"> <a href="#">${index}</a> </li>
                                        <#else>
                                            <li> <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a> </li>
                                        </#if>
                                    </#list>
                                <#elseif currentPage lte 4>
                                    <#list 1..9 as index>
                                        <#if currentPage == index>
                                            <li class="disabled"> <a href="#">${index}</a> </li>
                                        <#else>
                                            <li> <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a> </li>
                                        </#if>
                                    </#list>
                                </#if>
                            <#else>
                                <#list 1..productInfoPage.getTotalPages() as index>
                                    <#if currentPage == index>
                                        <li class="disabled"> <a href="#">${index}</a> </li>
                                    <#else>
                                        <li> <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a> </li>
                                    </#if>
                                </#list>
                            </#if>

                            <#if currentPage lt productInfoPage.getTotalPages()>
                                <li><a href="/sell/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
</html>