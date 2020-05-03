import com.cred.evaluators.ExpressionEvaluator;
import com.cred.evaluators.ExpressionEvaluatorImpl;
import com.cred.evaluators.FeatureEvaluator;
import com.cred.evaluators.FeatureEvaluatorImpl;
import com.cred.exceptions.FeatureToggleBaseException;
import com.cred.exceptions.InvalidExpressionException;
import com.cred.user.AccountStatus;
import com.cred.user.Address;
import com.cred.user.Gender;
import com.cred.user.User;
import com.cred.user.UserContext;
import com.cred.user.UserType;
import org.junit.Before;
import org.junit.Test;


import java.util.Map;

import static org.junit.Assert.assertEquals;
public class FeatureToggleTest {

  private static User user;
  private static FeatureEvaluator featureEvaluator;
  private static Map<String , Object> userContextMap;
  private static ExpressionEvaluator expressionEvaluator;

  @Before
  public void initializeSetup(){
      user =  new User("Tarun Garg",
          new Address("G2", "Outer Ring Road", "Bangalore", "Karnataka", "INDIA", "560101"),
          "tarungarg121@gmail.com", "9090909090",
          Gender.MALE, 62,
          AccountStatus.ACTIVE, UserType.PRIVILEGED);
      featureEvaluator = FeatureEvaluatorImpl.getInstance();
      userContextMap = UserContext.getInstance().getUserContext(user);
      expressionEvaluator = ExpressionEvaluatorImpl.getInstance();
  }

  @Test
  public void testSeniorCitizenFeature_success() throws FeatureToggleBaseException {
    String featureName = "Senior citizen";
    String conditionalExpression = "( age > 60 ) and ( gender == male or gender == female )";
    assertEquals(true , featureEvaluator.isAllowed(conditionalExpression,featureName,userContextMap));
  }

  @Test
  public void testSeniorCitizenFeature_failure() throws FeatureToggleBaseException {
    String featureName = "Senior citizen";
    String conditionalExpression = "( age > 54 ) and ( gender == male or gender == female )";
    assertEquals(true , featureEvaluator.isAllowed(conditionalExpression,featureName,userContextMap));
  }

  @Test
  public void testActiveMaleUser_failure() throws FeatureToggleBaseException {
    String featureName = "Active male user";
    userContextMap.put("accountstatus" , AccountStatus.INACTIVE);
    String conditionalExpression = "( AccountStatus == Active ) and ( gender == male )";
    assertEquals(false , featureEvaluator.isAllowed(conditionalExpression,featureName,userContextMap));
  }

  @Test
  public void testValidInfixExpression_success() throws InvalidExpressionException {
    String conditionalExpression = "( AccountStatus == Active ) and ( gender == male )";
    assertEquals("AccountStatus Active == gender male == and ",new ExpressionEvaluatorImpl().infixToPostfix(conditionalExpression));
  }

  @Test(expected = InvalidExpressionException.class)
  public void whenExceptionThrown_thenExpectationSatisfied() throws InvalidExpressionException{
    String conditionalExpression = "(AccountStatus == Active ) and ( gender == male )";
    assertEquals("AccountStatus Active == gender male == and ", new ExpressionEvaluatorImpl().infixToPostfix(conditionalExpression));
  }
}
