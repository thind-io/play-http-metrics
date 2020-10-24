package com.thindio.metrics.filter

import akka.stream.Materializer
import com.thindio.metrics.common.{HttpEndpointLatencyMetric, HttpStatusMetric}
import javax.inject.Inject
import play.api.mvc.{Filter, RequestHeader, Result}

import scala.concurrent.{ExecutionContext, Future}

class HttpMetricFilter @Inject()(httpEndpointLatencyMetric: HttpEndpointLatencyMetric,
                                 httpStatusMetric: HttpStatusMetric)(implicit val mat: Materializer,
                                                                   ec: ExecutionContext)
  extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {

    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime
      httpStatusMetric.counter(result.header.status,requestHeader.path)
      httpEndpointLatencyMetric.histogram(requestHeader.path,requestTime)
      result
    }
  }
}