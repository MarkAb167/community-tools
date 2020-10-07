package com.community.tools.util.statemachie.actions;

import com.community.tools.service.github.GitHubConnectService;
import com.community.tools.service.github.GitHubService;
import com.community.tools.service.slack.SlackService;
import com.community.tools.util.statemachie.Event;
import com.community.tools.util.statemachie.State;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class VerificationLoginAction implements Action<State, Event> {

  @Value("${askAboutProfile}")
  private String askAboutProfile;
  @Autowired
  private SlackService slackService;
  @Autowired
  private GitHubConnectService gitHubConnectService;
  @Autowired
  private GitHubService gitHubService;

  @Override
  public void execute(StateContext<State, Event> context) {
    String user = context.getExtendedState().getVariables().get("id").toString();
    String nickname = context.getExtendedState().getVariables().get("gitNick").toString();
    GHUser userGitLogin = new GHUser();
    try {
      userGitLogin = gitHubService.getUserByLoginInGitHub(nickname);
    } catch (GHFileNotFoundException e) {
      throw new RuntimeException(e);
    }
    slackService.sendPrivateMessage(slackService.getUserById(user),
        userGitLogin.getHtmlUrl().toString());
  }
}
