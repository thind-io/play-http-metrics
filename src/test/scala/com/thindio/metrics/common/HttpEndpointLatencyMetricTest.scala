package com.thindio.metrics.common

import com.codahale.metrics.{Histogram, MetricRegistry}
import org.mockito.Mockito._
import org.specs2.matcher.{MustThrownExpectations, Scope}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class HttpEndpointLatencyMetricTest extends Specification {
  "HttpEndPointLatencyMatric" should {
    "histogram" in new Context {
      val path = "/admin/metrics"
      val latency: Histogram = mock[Histogram]

      mockMetricRegistry.histogram(path + "_latency") returns (latency)
      httpEndpointLatencyMetric.histogram(path,1L)
      verify(mockMetricRegistry, times(1)).histogram(path + "_latency")
      verify(latency, times(1)).update(1L)
    }
  }

  trait Context extends Scope with Mockito with MustThrownExpectations {
    implicit val mockMetricRegistry = mock[MetricRegistry]
    val httpEndpointLatencyMetric : HttpEndpointLatencyMetric = new HttpEndpointLatencyMetric()
  }
}
