package main;

public class GUI_IsCheck {
	// 2020.04.23 ★★★추가사항★★★
		// 새 목록이 없으면 false를 반환
		// 새 목록만들기 프레임에서 생성버튼을 클릭시 true로 바뀜 
		
		private boolean isCheck = false;

		public boolean getisCheck() {
			return isCheck;
		}

		public void setCheck(boolean isCheck) {
			this.isCheck = isCheck;
		}
}
