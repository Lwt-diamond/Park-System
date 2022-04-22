package entity;

public class ParkSource {
    public String sourceNo;
    public String sourcePosition;
    public String sourceIsUsed;
    public ParkSource(String sourceNo, String sourcePosition, String sourceIsUsed) {
        super();
        this.sourceNo = sourceNo;
        this.sourcePosition = sourcePosition;
        this.sourceIsUsed = sourceIsUsed;
    }
    public String getSourceNo() {
        return sourceNo;
    }
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }
    public String getSourcePosition() {
        return sourcePosition;
    }
    public void setSourcePosition(String sourcePosition) {
        this.sourcePosition = sourcePosition;
    }
    public String getSourceIsUsed() {
        return sourceIsUsed;
    }
    public void setSourceIsUsed(String sourceIsUsed) {
        this.sourceIsUsed = sourceIsUsed;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParkSource other = (ParkSource) obj;
        if (sourceIsUsed == null) {
            if (other.sourceIsUsed != null)
                return false;
        } else if (!sourceIsUsed.equals(other.sourceIsUsed))
            return false;
        if (sourceNo == null) {
            if (other.sourceNo != null)
                return false;
        } else if (!sourceNo.equals(other.sourceNo))
            return false;
        if (sourcePosition == null) {
            if (other.sourcePosition != null)
                return false;
        } else if (!sourcePosition.equals(other.sourcePosition))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParkSource{" +
                "sourceNo='" + sourceNo + '\'' +
                ", sourcePosition='" + sourcePosition + '\'' +
                ", sourceIsUsed='" + sourceIsUsed + '\'' +
                '}';
    }
}
