package com.thindio.metrics.common

import com.codahale.metrics.{Counter, MetricRegistry}
import org.mockito.Mockito._
import org.specs2.matcher.{MustThrownExpectations, Scope}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class HttpStatusMetricTest extends Specification {
  "HttpStatusMetric" should {
    "counter" in new Context {
      val path = "/admin/metrics"
      val totalCounter: Counter = mock[Counter]
      val totalStatusCounter: Counter = mock[Counter]
      val totalStatusPathCounter: Counter = mock[Counter]
      val totalPathCounter : Counter = mock[Counter]

      mockMetricRegistry.counter("http_request_total") returns totalCounter
      mockMetricRegistry.counter("http_request_total_200") returns totalStatusCounter
      mockMetricRegistry.counter("http_request_admin_metrics_200") returns totalStatusPathCounter
      mockMetricRegistry.counter("http_request_admin_metrics_total") returns totalPathCounter

      httpStatusMetric.counter(200,path)
      verify(mockMetricRegistry, times(1)).counter("http_request_total")
      verify(mockMetricRegistry, times(1)).counter("http_request_total_200")
      verify(mockMetricRegistry, times(1)).counter("http_request_admin_metrics_200")
      verify(mockMetricRegistry, times(1)).counter("http_request_admin_metrics_total")

      verify(totalCounter, times(1)).inc()
      verify(totalStatusCounter, times(1)).inc()
      verify(totalStatusPathCounter, times(1)).inc()
      verify(totalPathCounter, times(1)).inc()
    }
  }

  trait Context extends Scope with Mockito with MustThrownExpectations {
    implicit val mockMetricRegistry = mock[MetricRegistry]
    val httpStatusMetric : HttpStatusMetric = new HttpStatusMetric
  }
}