<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/20
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理系统</title>
</head>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/lacale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
    $.getJSON('doinit_student.do',function(lsca){
        $('#classid').combobox({
            data:lsca,
            valueField:'cid',
            textField:'cname',
            panelHeight:'80',
            value:'1'
        });
    });
});
/******保存和修改*******/
$(function(){
    //添加时保存
    $("#btsave").click(function(){
        $.messager.progress();	// 显示进度条
        $('#stuform').form('submit', {
            url:'save_student.do',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function(code){
                if (code=='1') {
                    $.messager.alert('提示','保存成功');
                    $('#dg').datagrid('reload');    // 重新载入当前页面数据
                    $("#stuform")[0].reset();	//表单清空
                }else {
                    $.messager.alert('提示','保存失败');
                }
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
            }
        });
    });
    //修改时保存
    $("#btupdate").click(function(){
        $.messager.progress();	// 显示进度条
        $('#stuform').form('submit', {
            url:'update_student.do',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function(code){
                if (code=='1') {
                    $.messager.alert('提示','修改成功');
                    $('#dg').datagrid('reload');    // 重新载入当前页面数据
                    $("#stuform")[0].reset();	//表单清空
                }else {
                    $.messager.alert('提示','修改失败');
                }
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
            }
        });
    });
});
/******保存和修改end*******/
/********学生列表 *********/
$(function(){
    $("#dg").datagrid({
        url:'findPageAll_student.do',
        striped:'true',
        pagination:'false',
        pageList:[5,10,20,50,100],
        pageSize:5,
        pageNumber:1,
        scrollbarSize:10,
        loadMsg:'正在加载,请等待…',
        width:820,
        columns:[[
            {field:'sid',title:'编号',width:100,align:'center'},
            {field:'sname',title:'姓名',width:100,align:'center'},
            {field:'sex',title:'性别',width:100,align:'center'},
            {field:'saddress',title:'地址',width:100,align:'center'},
            {field:'sdate',title:'生日',width:100,align:'center'},
            {field:'cname',title:'班级',width:100,align:'center'},
            {field:'opt',title:'操作',width:200,align:'center',
                formatter: function(value,row,index){
                    var bt1="<input type='button' value='删除' onclick='delById("+row.sid+")'>";
                    var bt2="<input type='button' value='编辑' onclick='findById("+row.sid+")'>";
                    return bt1+'&nbsp;'+bt2;
                }
            }
        ]]
    });
});
/********学生列表end******/
/******删除、编辑*******/
function delById(sid){
    $.messager.confirm('确认','您确认想要删除此条记录吗？',function(r){
        if (r){
            $.post('delById_student.do?sid='+sid,function(code){
                if(code=='1'){
                    $.messager.alert('提示','删除成功');
                    $('#dg').datagrid('reload');    // 重新载入当前页面数据
                }else{
                    $.messager.alert('提示','删除失败');
                }
            });
        }
    });
}
function findById(sid){
    $.post('findById_student.do?sid='+sid,function(student){
        $('#stuform').form('load',{
            'sid':student.sid,
            'sname':student.sname,
            'sex':student.sex,
            'saddress':student.saddress,
            'sdate':student.sdate,
            'classid':student.classid
        });
    });
}
/******删除、编辑end*******/
</script>
<body>
<p align="center">学生列表
<hr/>
<!-- 展示表格 -->
<table id="dg" align="center"></table>
</p>
<p>
<form action="" method="post" enctype="multipart/form-data" name="stuform" id="stuform">
    <table width="550" border="1" align="center" cellpadding="1" cellspacing="0">
        <tr>
            <td colspan="2" align="center" bgcolor="#99FFCC">学生管理</td>
        </tr>
        <tr>
            <td width="120" align="center">姓名</td>
            <td width="303">
                <input type="text" name="sname"  id="sname" class="easyui-validatebox" data-options="required:true,missingMessage:'姓名'"/></td>
            <input type="hidden" name="sid"  id="sid"/></td>
        </tr>
        <tr>
            <td align="center">性别</td>
            <td><input name="sex" type="radio" id="radio" value="男" checked="checked" />男
                <input type="radio" name="sex" id="radio2" value="女" />女</td>
        </tr>
        <tr>
            <td align="center">地址</td>
            <td><input type="text" name="saddress" id="saddress"/></td>
        </tr>
        <tr>
            <td align="center">生日</td>
            <td><input name="sdate" type="date" id="sdate" value="1990-01-01" /></td>
        </tr>
        <tr>
        <td align="center">班级</td>
        <td>
            <input type="text" name="classid" id="classid" /></td>
        </tr>
        <tr>
            <td colspan="3" align="center" bgcolor="#99FFCC">
                <input type="button" name="btsave" id="btsave" value="保存" />
                <input type="button" name="btupdate" id="btupdate" value="修改" />
                <input type="button" name="btreset" id="btreset" value="取消" /></td>
        </tr>
    </table>
</form>
</p>
</body>
</html>
