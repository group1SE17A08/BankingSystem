package Entity;

import java.sql.Date;
import java.util.UUID;

public class Request {
    private String requestId;
    private String requestType;
    private String requestBy;
    private Date requestDate;
    private String requestContent;
    private boolean requestStatus;
    private String requestResolvedBy;

    public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Request(String requestType, String requestBy, Date requestDate, String requestContent,
                   boolean requestStatus, String requestResolvedBy) {
        this.requestId = generateRequestId();
        this.requestType = requestType;
        this.requestBy = requestBy;
        this.requestDate = requestDate;
        this.requestContent = requestContent;
        this.requestStatus = requestStatus;
        this.requestResolvedBy = requestResolvedBy;
    }
    
    public Request() {
    	 this.requestId = generateRequestId();
    }

    public String generateRequestId() {
        // Generate a random alphanumeric ID with 5 characters
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid.substring(0, 5);
    }

    // Getters and Setters

    public String getRequestId() {
        return requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestResolvedBy() {
        return requestResolvedBy;
    }

    public void setRequestResolvedBy(String requestResolvedBy) {
        this.requestResolvedBy = requestResolvedBy;
    }
}
