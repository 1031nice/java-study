package liveStudy;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class GithubAPIStudy {

    public static void main(String[] args) throws IOException {
        String token = "0000000000000000000000000000000000000000";
        GitHub github = new GitHubBuilder().withOAuthToken(token).build();

        GHRepository repository = github.getRepository("whiteship/live-study");
        List<GHIssue> issues = repository.getIssues(GHIssueState.CLOSED);
        SortedMap<String, Integer> map = new TreeMap<>();
        int week = 0;
        for(GHIssue issue : issues){
            week++;
            List<GHIssueComment> comments = issue.getComments();
            for(GHIssueComment comment : comments) {
                String userId = comment.getUser().getLogin();
                Integer count = map.get(userId);
                if(count == null){
                    map.put(userId, 1);
                }
                else if(count < week){ // 적어도 n주차일 때 참여 횟수가 n보다 큰 일이 없도록
                    map.put(userId, count+1);
                }
            }
        }

        map.forEach((k, v) -> System.out.println(k + " " + String.format("%.2f", v/18f*100) + "%"));
    }

}
