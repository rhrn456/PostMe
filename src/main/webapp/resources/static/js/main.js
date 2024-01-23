// 게시글을 로드
function loadPosts(page) {
    $.ajax({
        url: '/posts?page=' + page, // 게시글 데이터를 불러올 서버의 URL
        type: 'GET',
        success: function (response) {
            $('#posts-container').html(''); // 컨테이너 초기화
            response.posts.forEach(post => {
                $('#posts-container').append(
                    '<div class="post-preview">' +
                    '<h3>' + post.title + '</h3>' +
                    '<p>' + post.summary + '</p>' +
                    '<a href="/postDetail?id=' + post.id + '">자세히 보기</a>' +
                    '</div>'
                );
            });
        }
    });
}

// 페이지네이션 설정
function setupPagination(totalPages) {
    $('#pagination').html(''); // 페이지네이션 초기화
    for (let i = 1; i <= totalPages; i++) {
        $('#pagination').append(
            '<a href="#" onclick="loadPosts(' + i + '); return false;">' + i + '</a> '
        );
    }
}

// 게시글과 페이지네이션 로드
$(document).ready(function () {
    loadPosts(1); // 초기 페이지 로드
    setupPagination(10); // 총 10개의 페이지가 있다 가정
});
