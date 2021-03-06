package com.winsigns.investment.framework.hal;


import java.util.Arrays;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.Link;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * 支持link的替换功能
 * 
 * @author yimingjin
 * @since 0.0.4
 */
@XmlRootElement
public class Resource<T> extends ResourceSupport {

  private final T content;

  /**
   * Creates an empty {@link Resource}.
   */
  Resource() {
    this.content = null;
  }

  /**
   * Creates a new {@link Resource} with the given content and {@link Link}s (optional).
   * 
   * @param content must not be {@literal null}.
   * @param links the links to add to the {@link Resource}.
   */
  public Resource(T content, Link... links) {
    this(content, Arrays.asList(links));
  }

  /**
   * Creates a new {@link Resource} with the given content and {@link Link}s.
   * 
   * @param content must not be {@literal null}.
   * @param links the links to add to the {@link Resource}.
   */
  public Resource(T content, Iterable<Link> links) {

    Assert.notNull(content, "Content must not be null!");
    Assert.isTrue(!(content instanceof Collection),
        "Content must not be a collection! Use Resources instead!");
    this.content = content;
    this.add(links);
  }

  /**
   * Returns the underlying entity.
   * 
   * @return the content
   */
  @JsonUnwrapped
  @XmlAnyElement
  public T getContent() {
    return content;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.hateoas.ResourceSupport#toString()
   */
  @Override
  public String toString() {
    return String.format("Resource { content: %s, %s }", getContent(), super.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.hateoas.ResourceSupport#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }

    if (obj == null || !obj.getClass().equals(getClass())) {
      return false;
    }

    Resource<?> that = (Resource<?>) obj;

    boolean contentEqual =
        this.content == null ? that.content == null : this.content.equals(that.content);
    return contentEqual ? super.equals(obj) : false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.hateoas.ResourceSupport#hashCode()
   */
  @Override
  public int hashCode() {

    int result = super.hashCode();
    result += content == null ? 0 : 17 * content.hashCode();
    return result;
  }
}
