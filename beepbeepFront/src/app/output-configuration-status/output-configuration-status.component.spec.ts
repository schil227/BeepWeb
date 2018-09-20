import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutputConfigurationStatusComponent } from './output-configuration-status.component';

describe('OutputConfigurationStatusComponent', () => {
  let component: OutputConfigurationStatusComponent;
  let fixture: ComponentFixture<OutputConfigurationStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutputConfigurationStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutputConfigurationStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
