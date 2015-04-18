package com.geecat.movie.response;


public class MovieResponse {
private String name;
private String language;
private String date;
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the language
 */
public String getLanguage() {
	return language;
}
/**
 * @param language the language to set
 */
public void setLanguage(String language) {
	this.language = language;
}
/**
 * @return the date
 */
public String getDate() {
	return date;
}
/**
 * @param date the date to set
 */
public void setDate(String date) {
	this.date = date;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "MovieRequest [name=" + name + ", language=" + language + "]";
}

}
