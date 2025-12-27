from fastapi import FastAPI
from pydantic import BaseModel

from network_backend.ping_service import ping_service
from network_backend.traceroute_service import traceroute_service
from network_backend.tcp_scan import tcp_scan
from network_backend.udp_scan import scan_udp_ports
from network_backend.network_info import get_network_info

app = FastAPI()

class PingRequest(BaseModel):
    host: str

class PortScanRequest(BaseModel):
    host: str
    start_port: int
    end_port: int

class TracerouteRequest(BaseModel):
    host: str

@app.post("/ping")
def ping_api(data: PingRequest):
    return ping_service(data.host)

@app.post("/tcp-scan")
def tcp_scan_api(data: PortScanRequest):
    return tcp_scan(data.host, data.start_port, data.end_port)

@app.post("/udp-scan")
def udp_scan_api(data: PortScanRequest):
    return scan_udp_ports(data.host, data.start_port, data.end_port)

@app.post("/traceroute")
def traceroute_api(data: TracerouteRequest):
    return traceroute_service(data.host)

@app.get("/network-info")
def network_info_api():
    return get_network_info()
