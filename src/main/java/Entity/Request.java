package Entity;

import java.sql.Date;
<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.HashMap;
=======
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
import java.util.UUID;

public class Request {
    private String requestId;
    private String requestType;
    private String requestBy;
<<<<<<< HEAD
    private Timestamp requestDate;
    private String requestContent;
    private boolean requestStatus;
    private String requestResolvedBy;
    private String requestName;
    private String reqOwnerId;
    private HashMap<Double, Date> requestSavings;
    private HashMap<String, String> requestInformationChanging;
    private boolean rejected;
    
    
    
=======
    private Date requestDate;
    private String requestContent;
    private boolean requestStatus;
    private String requestResolvedBy;

>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
    public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

<<<<<<< HEAD
	public Request(String requestType, String requestBy, Timestamp requestDate, String requestContent,
=======
	public Request(String requestType, String requestBy, Date requestDate, String requestContent,
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
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

<<<<<<< HEAD
    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
=======
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
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
<<<<<<< HEAD

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public HashMap<Double, Date> getRequestSavings() {
		return requestSavings;
	}

	public void setRequestSavings(HashMap<Double, Date> requestSavings) {
		this.requestSavings = requestSavings;
	}

	public HashMap<String, String> getRequestInformationChanging() {
		return requestInformationChanging;
	}

	public void setRequestInformationChanging(HashMap<String, String> requestInformationChanging) {
		this.requestInformationChanging = requestInformationChanging;
	}

	public String getReqOwnerId() {
		return reqOwnerId;
	}

	public void setReqOwnerId(String reqOwnerId) {
		this.reqOwnerId = reqOwnerId;
	}

	public boolean getRejected() {
		return rejected;
	}

	public void setRejected(boolean isRejected) {
		this.rejected = isRejected;
	}
=======
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
}
