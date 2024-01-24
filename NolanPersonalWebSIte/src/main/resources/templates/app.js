// app.js
function showPost(postId) {
    $.get("/post/" + postId, function(data) {
        $("#blog-post").html(data);
        alert("hello")
    });
}
