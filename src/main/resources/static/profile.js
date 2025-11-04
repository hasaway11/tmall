async function showProfile() {
  // 이미지가 1MB보다 크면 업로드 중단
  const profile = document.getElementById('profile').files[0];
  const maxSize = 1024*1024;

  if(profile.size>maxSize) {
    alert('프로필 사진은 1MB이하여야 합니다');
    return;
  }

  // 자바스크립트에서 파일 업로드를 담당하는 객체인 FormData를 생성하는 방법
  // - <form> 요소를 이용해 생성 : new FormData(document.getElementById(폼))
  // - 빈 formData를 생성한 다음 append로 하나씩 추가
  const formData = new FormData();
  formData.append('profile', profile);
  try {
    const response = await axios.post('/api/profile/new', formData);
    $('#show-profile').css('visibility','visible').attr('src', response.data.profileUrl);
    $('#profile-name').val(response.data.profileName);
  } catch(err) {
    console.log(err);
  }
}

async function changeProfile() {
  const profile = $('#profile-name').val();
  if(profile=='') {
    alert("새로운 프로필을 선택하지 않았습니다");
    return;
  }

  const params = {profile: profile};
  try {
    await axios.patch("/api/member/profile", new URLSearchParams(params));
    alert("프로필을 변경했습니다");
    $('#profile')[0].files[0]='';
  } catch(err) {
    location.reload();
  }
}