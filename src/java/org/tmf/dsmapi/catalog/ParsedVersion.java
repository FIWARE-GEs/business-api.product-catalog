package org.tmf.dsmapi.catalog;

import java.io.Serializable;

/**
 *
 * @author bahman.barzideh
 *
 */
public class ParsedVersion implements Serializable {
    public final static int MINIMUM_DEPTH = 1;

    private Integer majorVersion;
    private Integer minorVersion;

    public ParsedVersion() {
        initialize_();
    }

    public Integer getMajorVersion() {
        return majorVersion;
    }

    public Integer getMinorVersion() {
        return minorVersion;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + (this.majorVersion != null ? this.majorVersion.hashCode() : 0);
        hash = 89 * hash + (this.minorVersion != null ? this.minorVersion.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final ParsedVersion other = (ParsedVersion) object;
        if (Utilities.areEqual(this.majorVersion, other.majorVersion) == false) {
            return false;
        }
        if (Utilities.areEqual(this.minorVersion, other.minorVersion) == false) {
            return false;
        }

        return true;
    }

    public String load(String input) {
        if (load_(input) == false) {
            throw new IllegalArgumentException("'" + input + "' is not a valid version.");
        }

        return format_();
    }

    public boolean isValid() {
        return (majorVersion != null) ? true : false;
    }

    public boolean isEqualTo(ParsedVersion other) {
        if (other == null) {
            return false;
        }

        if (Utilities.areEqual(this.majorVersion, other.majorVersion) == false) {
            return false;
        }

        if (Utilities.areEqual(this.minorVersion, other.minorVersion) == false) {
            return false;
        }

        return true;
    }

    public boolean isGreaterThan(ParsedVersion other) {
        if (other == null || this.majorVersion == null) {
            return false;
        }

        int compare = this.majorVersion.compareTo(other.majorVersion);
        if (compare < 0) {
            return false;
        }

        if (compare > 0) {
            return true;
        }

        return (this.minorVersion.compareTo (other.minorVersion) > 0) ? true : false;
    }

    private boolean load_(String input) {
        majorVersion = minorVersion = null;

        if (input == null) {
            return false;
        }

        String parts [] = input.split ("\\.");
        if (parts.length != 2) {
            return false;
        }

        try {
            majorVersion = Integer.parseInt(parts [0]);
        }
        catch (Exception ex) {
            return false;
        }

        try {
            minorVersion = Integer.parseInt(parts [1]);
        }
        catch (Exception ex) {
            initialize_();
            return false;
        }

        return true;
    }

    private String format_() {
        if (majorVersion == null) {
            return null;
        }

        return (majorVersion + "." + minorVersion);

    }

    private void initialize_() {
        majorVersion = null;
        minorVersion = null;
    }

}
