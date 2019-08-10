<html>
    <#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">

        <#include "../common/nav.ftl">

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/sell/seller/category/save" method="post">
                            <div class="form-group">
                                <label>类目名称</label>
                                <input type="text" class="form-control" value="${(productCategory.categoryName)!''}" name="categoryName"/>
                            </div>
                            <div class="form-group">
                                <label>类目编号</label>
                                <input type="text" class="form-control" value="${(productCategory.categoryType)!''}" name="categoryType"/>
                            </div>
                            <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>