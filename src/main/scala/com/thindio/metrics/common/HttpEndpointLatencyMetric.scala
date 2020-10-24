package com.thindio.metrics.common

import com.codahale.metrics.MetricRegistry
import javax.inject.{Inject, Singleton}

@Singleton
class HttpEndpointLatencyMetric @Inject()(implicit val registry: MetricRegistry) {

  def histogram(path: String, latency: Long)  {
    registry.histogram(path + "_latency").update(latency)
  }
}
