# webSocket
개발 일지 (메모장에 작성해 두었던 내용을 올립니다.)

2019/7/22
- project 시작, 개요 작성
- 주제, 개요, 기능, 기술, 설계 작성
- 프로젝트 파일 생성

2019/7/23
- db 설정 (스키마, 유저, 권한)
- application.properties에 환경설정
- security 기본 설정
- thymeleaf 레이아웃 사용 (html5 boilerplate)
- header, footer, home 화면의 구성
- controller 구성
- memberdto 클래스 작성

2019/7/24
- spring security 설정

2019/7/25
- 로그인, 로그아웃, 회원가입 구현
- form 태그와 a 태그의 사용 주의
- spring security config 설정
- custom user, userservice 구현

2019/7/27
- 로그인, 로그아웃 구현
- 로그인 성공시 defaulturl 설정
- 컨트롤러 수정
- 부트스트랩 적용
- th:href 주의

2019/7/29
- 로그인, 로그아웃, 회원가입 페이지 구현
- 다음 작업: 마이페이지, 친구목록

2019/7/31
- 마이페이지 구현
- frienddto 설계(테이블 분리 이유: member와 member 는 다대다 관계이므로)
- 복합키 클래스 설정 embeddable 어노테이션 활용
- memberdto에 friend list가 있을 필요 없음
- 다음 작업: 친구목록

2019/8/1
- friendcontroller, friendrepository 생성
- friend view 생성
- friend crud 작업 
친구요청하기 
친구요청보기 -> 수락, 거절
친구목록보기 
친구목록제거

친구요청하기
일방적요청 friendrepo에 save하면됨

친구요청보기 쿼리(미완성)
select friend
from friendship
where friend not in(
select f1.friend
from friendship f1 inner join friendship f2
on f1.user = f2.friend and f1.friend = f2.user);


친구목록보기 쿼리 
select f1.friend
from friendship f1 inner join friendship f2 
on f1.user = f2.friend and f1.friend = f2.user
where f1.user = "admin";

친구목록제거
일방적제거 user, friend 이용해서 양쪽제거 

2019/8/3
REST url 정의
- 친구목록보기	/socialChat/friend/{myId} -> myId의 친구목록 반환 (get)
- 사용자 검색	/socialChat/friend/{myId}/{userName} -> myId를 제외한 userName 으로 사용자 검색 (get)
- 친구요청하기 	/socialChat/friend/{user}/{friend} -> user에 friend 추가 (post)
- 친구목록제거 	/socialChat/friend/{user}/{friend} (delete) -> user에 friend 제거 (delete)
- 친구요청보기	/socialChat/friendRequest/{userId} -> userId의 친구요청리스트 반환 (get)
 -수락 		/socialChat/friendRequest/{userId}/{friendId} (post)  -> userId에 요청으로 들어온 friendId 추가 (post)
 -거절 		/socialChat/friendRequest/{userId}/{friendId} (delete) -> friendId에 요청으로 들어온 userId(친구 추가 요청한 사람) 제거 (delete)
rest로 할 필요가 없음 
- resource가 복잡하지 않음 - 친구 아이디만 주고 받음

---> friend.html 
친구목록보기 ok
친구요청하기 ok
친구목록제거 ok

친구요청보기
-> 수락, 거절

다음작업
친구요청하기부터 시작 
- 친구검색기능의 완성 (친구요청 버튼 눌렀을때 데이터 제대로 가져오도록)

2019/8/4
- class 속성과 id 속성의 차이점 구분 : id 속성은 유일값이다.
- rest api 에서 security 적용되면 ajax 요청시 csrf 토큰값 넣어야함
- 친구프로필보기 friendInfo 간단히 컨트롤러와 뷰 작성

- 다음작업: 친구목록제거, 친구프로필보기 완성

2019/8/5
- rest로 변경 친구목록작업 전체 + 사용자검색
- 다음작업: 친구목록보기 ajax 처리후 뷰 완성, 친구요청보기 작업 시작

2019/8/6
- 친구목록보기 rest api로 완성

친구요청보기 쿼리
select user
from friendship
where user not in(select f1.friend
from friendship f1 inner join friendship f2 
on f1.user = f2.friend and f1.friend = f2.user) and friend = "admin";
친구요청보기 수락 쿼리
친구요청보기 거절 쿼리

다음작업 - 친구요청, 수락, 거절 완성

2019/8/7
친구요청 목록보기 쿼리작성 및 jpa 연동

다음작업 - 친구작업완료 및 중간 검토

2019/8/8
- 친구작업완료

2019/8/17
- stomp와 websocket 의 차이
https://www.callicoder.com/spring-boot-websocket-chat-example/
- chatroom dto, repository 생성
다음작업 - chatcontroller 및 서버 쪽 구현 완료

2019/8/19
- chatroom 작업
crud
create 방만들기
read 방목록
update 방수정 (제외)
delete 방삭제 - 아직안함

서버쪽 수정 필요
클라이언트 일부 구현

다음작업 - 서버쪽 완성과 클라이언트 완성

2019/8/20
채팅 입장 퇴장 구현 완료


다음작업 - 참여자 수 표시, 프론트 디자인

2019/8/21
마지막 구현 남은 것
- 친구 접속/미접속 확인 ok
- remember me 구현 ok
- 채팅방 삭제 ok
- friendRequest 친구 요청 보기 쿼리 수정

다음작업 - 프론트 디자인

2019/8/22
프로젝트 종료
