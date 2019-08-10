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
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOPage.getContent() as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnums().getMsg()}</td>
                                    <td>${orderDTO.getPayStatusEnums().getMsg()}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEnums().getMsg() == "新订单">
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                                <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>

                            <#if orderDTOPage.getTotalPages() gt 9>
                                <#if currentPage gt 4 && orderDTOPage.getTotalPages()-currentPage gt 4 >
                                    <#list currentPage-4..currentPage+4 as index>
                                        <#if currentPage == index>
                                            <li class="disabled"> <a href="#">${index}</a> </li>
                                        <#else>
                                            <li> <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a> </li>
                                        </#if>
                                    </#list>
                                <#elseif orderDTOPage.getTotalPages()-currentPage lte 4>
                                    <#list orderDTOPage.getTotalPages()-9 .. orderDTOPage.getTotalPages() as index>
                                        <#if currentPage == index>
                                            <li class="disabled"> <a href="#">${index}</a> </li>
                                        <#else>
                                            <li> <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a> </li>
                                        </#if>
                                    </#list>
                                <#elseif currentPage lte 4>
                                    <#list 1..9 as index>
                                        <#if currentPage == index>
                                            <li class="disabled"> <a href="#">${index}</a> </li>
                                        <#else>
                                            <li> <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a> </li>
                                        </#if>
                                    </#list>
                                </#if>
                            <#else>
                                <#list 1..orderDTOPage.getTotalPages() as index>
                                    <#if currentPage == index>
                                        <li class="disabled"> <a href="#">${index}</a> </li>
                                    <#else>
                                        <li> <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a> </li>
                                    </#if>
                                </#list>
                            </#if>

                            <#if currentPage lt orderDTOPage.getTotalPages()>
                                <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        提醒
                    </h4>
                </div>
                <div class="modal-body">
                    你有新的订单
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="javascript:document.getElementById('notice').pause()" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" onclick="location.reload()" class="btn btn-primary">查看</button>
                </div>
            </div>

        </div>

    </div>

    <audio id="notice" loop="loop">
        <source src="\sell\mp3\tt.mp3" type="audio/mpeg" />
    </audio>

    <script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>

    var websocket = null ;
     if ('websocket' in window){
         websocket = new WebSocket('ws://dzahuo.natapp1.cc/sell/webSocket');
     }else
     {
         alert('该浏览器不支持websocket');
     }

     websocket.onopen = function (event){
         console.log('建立连接。。。');
     }

     websocket.onclose = function (event) {
         console.log('关闭连接。。。');
     }

     websocket.onmessage = function (event) {
         console.log('收到消息。。。'+event.data);
         //弹窗提醒，播放响铃
         $('#myModal').modal('show');
         document.getElementById('notice').play();
     }
     
     websocket.onbeforeunload = function () {
         websocket.close();
     }

     websocket.onerror = function () {
         alert('websocket通信发生错误！');
     }
         
</script>
</body>
</html>