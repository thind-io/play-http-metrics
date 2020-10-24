package com.thindio.metrics.common

import com.codahale.metrics.MetricRegistry
import javax.inject.{Inject, Singleton}

@Singleton
class HttpStatusMetric @Inject()(implicit val registry: MetricRegistry) {

  def counter(statusCode: Int, path: String)  {
    registry.counter("http_request_total").inc()
    registry.counter("http_request_total_" + statusCode.toString).inc()
    val name = "http_request" + sanitize(path)
    registry.counter(name + "_" + statusCode.toString).inc()
    registry.counter(name + "_total").inc()
  }

  private def sanitize(str: String) : String = {
     str.replace("/","_")
  }
}
