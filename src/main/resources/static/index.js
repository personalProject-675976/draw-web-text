// 给提交按钮绑定事件
$('#export').click
(
    function ()
    {
        if ($('input[type=text]').val() == '')
        {
            alert('必须输入网页URL');
        }
        else
        {
            getText();
        }
    }
);

function getText ()
{
    $.ajax
    (
        {
            url : '/getText?url=' + $('input[type=text]').val(),
            type : 'GET',
            dataType : 'json',
            success : function (data)
            {
                if (data.success)
                {
                    $('#text').html(data.data);
                }
                else
                {
                    $('#text').html(data.msg);
                }
            },
            error : function ()
            {
                $('#text').html('请求失败');
            }
        }
    );
}