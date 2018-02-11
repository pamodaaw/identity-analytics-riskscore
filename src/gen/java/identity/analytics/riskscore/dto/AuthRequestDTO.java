package identity.analytics.riskscore.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class AuthRequestDTO  {
  
  
  @NotNull
  private String contextId = null;
  
  @NotNull
  private String eventId = null;
  
  @NotNull
  private String eventType = null;
  
  @NotNull
  private Boolean authenticationSuccess = null;
  
  @NotNull
  private String username = null;
  
  @NotNull
  private String localUsername = null;
  
  @NotNull
  private String userStoreDomain = null;
  
  @NotNull
  private String tenantDomain = null;
  
  @NotNull
  private String remoteIp = null;
  
  @NotNull
  private String region = null;
  
  @NotNull
  private String inboundAuthType = null;
  
  @NotNull
  private String serviceProvider = null;
  
  @NotNull
  private Boolean rememberMeEnabled = null;
  
  @NotNull
  private Boolean forceAuthEnabled = null;
  
  @NotNull
  private Boolean passiveAuthEnabled = null;
  
  @NotNull
  private String rolesCommaSeparated = null;
  
  @NotNull
  private String authenticationStep = null;
  
  @NotNull
  private String identityProvider = null;
  
  @NotNull
  private Boolean authStepSuccess = null;
  
  @NotNull
  private String stepAuthenticator = null;
  
  @NotNull
  private Boolean isFirstLogin = null;
  
  @NotNull
  private String identityProviderType = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("contextId")
  public String getContextId() {
    return contextId;
  }
  public void setContextId(String contextId) {
    this.contextId = contextId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("eventId")
  public String getEventId() {
    return eventId;
  }
  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("eventType")
  public String getEventType() {
    return eventType;
  }
  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("authenticationSuccess")
  public Boolean getAuthenticationSuccess() {
    return authenticationSuccess;
  }
  public void setAuthenticationSuccess(Boolean authenticationSuccess) {
    this.authenticationSuccess = authenticationSuccess;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("localUsername")
  public String getLocalUsername() {
    return localUsername;
  }
  public void setLocalUsername(String localUsername) {
    this.localUsername = localUsername;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("userStoreDomain")
  public String getUserStoreDomain() {
    return userStoreDomain;
  }
  public void setUserStoreDomain(String userStoreDomain) {
    this.userStoreDomain = userStoreDomain;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("tenantDomain")
  public String getTenantDomain() {
    return tenantDomain;
  }
  public void setTenantDomain(String tenantDomain) {
    this.tenantDomain = tenantDomain;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("remoteIp")
  public String getRemoteIp() {
    return remoteIp;
  }
  public void setRemoteIp(String remoteIp) {
    this.remoteIp = remoteIp;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("region")
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("inboundAuthType")
  public String getInboundAuthType() {
    return inboundAuthType;
  }
  public void setInboundAuthType(String inboundAuthType) {
    this.inboundAuthType = inboundAuthType;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("serviceProvider")
  public String getServiceProvider() {
    return serviceProvider;
  }
  public void setServiceProvider(String serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("rememberMeEnabled")
  public Boolean getRememberMeEnabled() {
    return rememberMeEnabled;
  }
  public void setRememberMeEnabled(Boolean rememberMeEnabled) {
    this.rememberMeEnabled = rememberMeEnabled;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("forceAuthEnabled")
  public Boolean getForceAuthEnabled() {
    return forceAuthEnabled;
  }
  public void setForceAuthEnabled(Boolean forceAuthEnabled) {
    this.forceAuthEnabled = forceAuthEnabled;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("passiveAuthEnabled")
  public Boolean getPassiveAuthEnabled() {
    return passiveAuthEnabled;
  }
  public void setPassiveAuthEnabled(Boolean passiveAuthEnabled) {
    this.passiveAuthEnabled = passiveAuthEnabled;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("rolesCommaSeparated")
  public String getRolesCommaSeparated() {
    return rolesCommaSeparated;
  }
  public void setRolesCommaSeparated(String rolesCommaSeparated) {
    this.rolesCommaSeparated = rolesCommaSeparated;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("authenticationStep")
  public String getAuthenticationStep() {
    return authenticationStep;
  }
  public void setAuthenticationStep(String authenticationStep) {
    this.authenticationStep = authenticationStep;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("identityProvider")
  public String getIdentityProvider() {
    return identityProvider;
  }
  public void setIdentityProvider(String identityProvider) {
    this.identityProvider = identityProvider;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("authStepSuccess")
  public Boolean getAuthStepSuccess() {
    return authStepSuccess;
  }
  public void setAuthStepSuccess(Boolean authStepSuccess) {
    this.authStepSuccess = authStepSuccess;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("stepAuthenticator")
  public String getStepAuthenticator() {
    return stepAuthenticator;
  }
  public void setStepAuthenticator(String stepAuthenticator) {
    this.stepAuthenticator = stepAuthenticator;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("isFirstLogin")
  public Boolean getIsFirstLogin() {
    return isFirstLogin;
  }
  public void setIsFirstLogin(Boolean isFirstLogin) {
    this.isFirstLogin = isFirstLogin;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("identityProviderType")
  public String getIdentityProviderType() {
    return identityProviderType;
  }
  public void setIdentityProviderType(String identityProviderType) {
    this.identityProviderType = identityProviderType;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthRequestDTO {\n");
    
    sb.append("  contextId: ").append(contextId).append("\n");
    sb.append("  eventId: ").append(eventId).append("\n");
    sb.append("  eventType: ").append(eventType).append("\n");
    sb.append("  authenticationSuccess: ").append(authenticationSuccess).append("\n");
    sb.append("  username: ").append(username).append("\n");
    sb.append("  localUsername: ").append(localUsername).append("\n");
    sb.append("  userStoreDomain: ").append(userStoreDomain).append("\n");
    sb.append("  tenantDomain: ").append(tenantDomain).append("\n");
    sb.append("  remoteIp: ").append(remoteIp).append("\n");
    sb.append("  region: ").append(region).append("\n");
    sb.append("  inboundAuthType: ").append(inboundAuthType).append("\n");
    sb.append("  serviceProvider: ").append(serviceProvider).append("\n");
    sb.append("  rememberMeEnabled: ").append(rememberMeEnabled).append("\n");
    sb.append("  forceAuthEnabled: ").append(forceAuthEnabled).append("\n");
    sb.append("  passiveAuthEnabled: ").append(passiveAuthEnabled).append("\n");
    sb.append("  rolesCommaSeparated: ").append(rolesCommaSeparated).append("\n");
    sb.append("  authenticationStep: ").append(authenticationStep).append("\n");
    sb.append("  identityProvider: ").append(identityProvider).append("\n");
    sb.append("  authStepSuccess: ").append(authStepSuccess).append("\n");
    sb.append("  stepAuthenticator: ").append(stepAuthenticator).append("\n");
    sb.append("  isFirstLogin: ").append(isFirstLogin).append("\n");
    sb.append("  identityProviderType: ").append(identityProviderType).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
