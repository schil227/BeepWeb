import { TestBed, inject } from '@angular/core/testing';

import { OutputConfigurationApiService } from './output-configuration-api.service';

describe('OutputConfigurationApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OutputConfigurationApiService]
    });
  });

  it('should be created', inject([OutputConfigurationApiService], (service: OutputConfigurationApiService) => {
    expect(service).toBeTruthy();
  }));
});
