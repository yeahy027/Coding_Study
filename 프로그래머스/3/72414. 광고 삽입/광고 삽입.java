class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {  
        int play = timeToSec(play_time);
        int adv = timeToSec(adv_time);
        long[] dp = new long[play + 1];
        
        for (String log : logs) {
            String[] times = log.split("-");
            int start = timeToSec(times[0]);
            int end = timeToSec(times[1]);
            
            dp[start] += 1;
            dp[end] -= 1;
        }
        
        // 첫번째 누적합 : 동시 시청자 수 구하기
        for (int i = 1; i <= play; i++) {
            dp[i] += dp[i - 1];
        }
        
        
        // 두번째 누적합 : 누적 시청 시간 구하기
        for (int i = 1; i <= play; i++) {
            dp[i] += dp[i - 1];
        }
        
        int advStart = 0;
        long maxTime = dp[adv - 1];
        
        for (int i = adv; i <= play; i++) {
            long temp = dp[i] - dp[i - adv];
            if (temp > maxTime) {
                maxTime = temp;
                advStart = i - adv + 1;
            }
        }
        
        return timeToStr(advStart);
        
    }
    
    // 문자열로 주어진 시간 초 단위로 변환
    private int timeToSec(String time) {
        String[] temp = time.split(":");
        
        int h = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int s = Integer.parseInt(temp[2]);
        
        return h * 3600 + m * 60 + s;
    }
    
    // 초 단위의 시간 문자열로 변환
    private String timeToStr(int seconds) {
        int h = seconds / 3600;
        seconds %= 3600;
        int m = seconds / 60;
        int s = seconds % 60;
        
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}